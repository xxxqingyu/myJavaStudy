package DemoMyBatis.MyBatisStart;

import java.util.List;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

import DemoMyBatis.MyBatisStart.beans.Customer;
import DemoMyBatis.MyBatisStart.beans.Day;
import DemoMyBatis.MyBatisStart.mapper.CustomerMapper;
import DemoMyBatis.MyBatisStart.tools.DBTools;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SqlSession session= DBTools.getSession();
    	CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
    	
    	List<Customer> customer =customerMapper.getCustomerDetails(1);
    	
        System.out.println( "Hello World!" );
        
        SQL sql= new SQL() {
        	{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
            SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
            FROM("PERSON P");
            FROM("ACCOUNT A");
            INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
            INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
            WHERE("P.ID = A.ID");
            WHERE("P.FIRST_NAME like ?");
            OR();
            WHERE("P.LAST_NAME like ?");
            GROUP_BY("P.ID");
            HAVING("P.LAST_NAME like ?");
            OR();
            HAVING("P.FIRST_NAME like ?");
            ORDER_BY("P.ID");
            ORDER_BY("P.FULL_NAME");
          }
        };
         
       //Day.class.is
       String name=sql.getClass().getName();
    }
}
