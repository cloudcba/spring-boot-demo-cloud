package com.neo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.*;

@EnableScheduling
@Configuration
public class TaskTimer {

//    private  String treeStr="";

    private int n = 0;

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    //    @Scheduled(cron = "0/30 * * * * ?")
    public void myTimes() {
        //vb_mock_group_stu_num
//        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateFormat1.format(new Date()));
        //String sql="select name from t_meta where meta_id=10005288";
        String sql = "select name from t_meta";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        for (Map map : maps) {
            String name = map.get("name").toString();
            Map<Integer, String> treeStrMap = new HashMap<>();
            Map<Integer, String> vb_mock_group_stu_num = queryTaskTree(name, 0, treeStrMap, "");
            System.out.println(vb_mock_group_stu_num);
            for (int key : vb_mock_group_stu_num.keySet()) {
                String s = vb_mock_group_stu_num.get(key);
                String sql1 = "insert into job_tree (name,treeInfo) values ('" + name + "','" + s + "') ";
                jdbcTemplate.execute(sql1);

            }

        }

    }

    /**
     * 根据task_name处理任务上游，存在重名任务情况，舍弃
     *
     * @param table_name
     * @param length
     * @param treeStrMap
     * @return
     */
    private Map<Integer, String> queryTaskTree(String table_name, int length, Map<Integer, String> treeStrMap, String treeStr) {
        String t_sql = "select if(t34.job_name is not null, t34.job_name, t12.job_name) as job_name from   (select job_name  from  (select upstream_database , upstream_table   from hql_lineage_table_level_up  where job_name = '" + table_name + "' and status = '1' ) as t1 join  hql_lineage_table_level_down as t2  on t1.upstream_database = t2.downstream_database and t1.upstream_table = t2.downstream_table where t2.status = '1' group by t2.job_name ) t12  left join  ( select t3.job_name as view_job_name, t4.job_name as job_name  from (select job_name, upstream_database , upstream_table  from hql_lineage_table_level_up where left(job_name, 5) = 'view-' and status = '1' ) as t3  join  hql_lineage_table_level_down as t4  on t3.upstream_database = t4.downstream_database and t3.upstream_table = t4.downstream_table  where t4.status = '1'  group by t3.job_name, t4.job_name ) t34 on t12.job_name = t34.view_job_name";
        //String t_sql = "select t3.meta_id,t3.depend_meta_id,t3.name,t4.name as depend_name from (select t1.depend_meta_id,t1.meta_id,t2.name from t_depend t1 join t_meta t2 where t1.meta_id=t2.meta_id and t2.name='" + table_name + "') t3 join t_meta t4 where t3.depend_meta_id=t4.meta_id;"
        t_sql = "select t4.name as job_name from (select t1.depend_meta_id,t1.meta_id,t2.name from t_depend t1 join t_meta t2 where t1.meta_id=t2.meta_id and t2.name='" + table_name + "') t3 join t_meta t4 where t3.depend_meta_id=t4.meta_id";

        try {
            List<Map<String, Object>> delayExecList = jdbcTemplate.queryForList(t_sql);

            //去掉自依赖情况
            List<Map> newList = new ArrayList<>();
            for (int i = 0; i < delayExecList.size(); i++) {
                if (null != delayExecList.get(i).get("job_name") && !table_name.equals(delayExecList.get(i).get("job_name"))) {
                    newList.add(delayExecList.get(i));
                }
            }

            //处理闭环情况

            String[] treeArray = treeStr.split(",");
            boolean isCycle = false;
            for (int i = 0; i < treeArray.length; i++) {
                String nodeName = treeArray[i];
                if (null != nodeName && nodeName.equals(table_name)) {
                    //形成闭环时将不再拼接链条
                    isCycle = true;
                }
            }

//            if (nodeNameStrList.contains(table_name)) {
//                isCycle = true;
//            }

            if (!isCycle) {
                treeStr = treeStr + table_name + ",";
            }

            if (newList.size() == 0) {
                if (length >= 2) {//对上层2个以上依赖的进行处理
                    if (!treeStrMap.containsValue(treeStr)) {
                        treeStrMap.put(treeStrMap.size(), treeStr);
                    } else {
                        isCycle = true;
                    }

                    n++;
                    treeStr = treeStr.replace(table_name + ",", "");
                    if (n == length) {
                        treeStr = "";
                    }
                } else {
                    if (!treeStrMap.containsValue(treeStr)) {
                        treeStrMap.put(treeStrMap.size(), treeStr);
                    } else {
                        isCycle = true;
                    }
                    treeStr = "";
                }

            }
//            nodeNameStrList.add(table_name);
            if (newList.size() > 0 && !isCycle) {
                for (int i = 0; i < newList.size(); i++) {
                    String depend_name = (String) newList.get(i).get("job_name");
                    queryTaskTree(depend_name, newList.size(), treeStrMap, treeStr);
                }
            }


        } catch (DataAccessException e) {
            //logger.info("数据库连接异常：", e.getMessage());
        }
        return treeStrMap;
    }
}
