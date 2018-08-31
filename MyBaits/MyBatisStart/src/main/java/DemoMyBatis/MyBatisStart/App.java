package DemoMyBatis.MyBatisStart;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DemoMyBatis.MyBatisStart.beans.Customer;
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
    }
}
