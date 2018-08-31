package DemoMyBatis.MyBatisStart.mapper;

import java.util.List;

import DemoMyBatis.MyBatisStart.beans.Customer;

public interface CustomerMapper {
	List<Customer> getCustomerDetails(long id);
}
