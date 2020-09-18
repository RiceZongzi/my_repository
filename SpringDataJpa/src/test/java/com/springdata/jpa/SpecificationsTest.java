package com.springdata.jpa;

import com.springdata.jpa.dao.CustomerDao;
import com.springdata.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * @Author ：by wgz
 * @description：
 * @Date ：Created in 2020/9/18 9:52
 * @Version: $
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecificationsTest extends Thread{

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件，查询单个对象
     *
     */
    @Test
    public void testSpec1() {
        /*
         * 自定义查询条件
         *      1.实现Specification接口（提供泛型：查询的对象类型）
         *      2.实现toPredicate方法（构造查询条件）
         *      3.需要借助方法参数中的两个参数（
         *          root：获取需要查询的对象属性
         *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
         *       ）
         *  案例：根据客户名称查询，查询客户名为张铁的客户
         *          查询条件
         *              1.查询方式
         *                  cb对象
         *              2.比较的属性名称
         *                  root对象
         *
         */
        Specification<Customer> spec = (root, query, cb) -> {
            //1.获取比较的属性
            Path<Object> custName = root.get("custName");
            //2.构造查询条件：select * from cst_customer where cust_name = '张铁'
            /*
             * 第一个参数：需要比较的属性（path对象）
             * 第二个参数：当前需要比较的取值
             */
            return cb.equal(custName, "张铁");
        };
        System.out.println(customerDao.findOne(spec));
    }

    /**
     * 多条件查询
     *
     */
    @Test
    public void testSpec2() {
        /*
         *  root:获取属性
         *      客户名
         *      所属行业
         *  cb：构造查询
         *      1.构造客户名的精准匹配查询
         *      2.构造所属行业的精准匹配查询
         *      3.将以上两个查询联系起来
         */
        Specification<Customer> spec = (root, query, cb) -> {
            // 客户名
            Path<Object> custName = root.get("custName");
            // 所属行业
            Path<Object> custIndustry = root.get("custIndustry");

            // 构造查询
            // 1.构造客户名的精准匹配查询
            // 第一个参数，path（属性），第二个参数，属性的取值
            Predicate p1 = cb.equal(custName, "张铁");
            // 2..构造所属行业的精准匹配查询
            Predicate p2 = cb.equal(custIndustry, "IT");
            // 3.将多个查询条件组合到一起：
            // 组合
            // and，与关系：满足条件一并且满足条件二，cb.and();
            // or，或关系：满足条件一或满足条件二即可，cb.or();
            return cb.and(p1, p2);
        };
        System.out.println(customerDao.findOne(spec));
    }

    /**
     * 完成根据客户名称的模糊匹配，返回客户列表
     *      客户名称以"张"开头
     *
     * equal，直接的到path对象（属性），然后进行比较即可
     * gt、lt、ge、le、like，得到path对象，根据path指定比较的参数类型，再去进行比较
     *      指定参数类型：path.as(类型的字节码对象)
     */
    @Test
    public void testSpec3() {
        // 构造查询条件
        Specification<Customer> spec = (root, query, cb) -> {
            // 查询属性：客户名
            Path<Object> custName = root.get("custName");
            // 查询方式：模糊匹配
            return cb.like(custName.as(String.class), "张%");
        };
        customerDao.findAll(spec).forEach(customer -> System.out.println(customer));
        // 添加排序
        // 创建排序对象,需要调用构造方法实例化sort对象
        // 第一个参数：排序的顺序（倒序，正序）
        //   Sort.Direction.DESC，降序
        //   Sort.Direction.ASC，升序
        // 第二个参数：排序的属性名称
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        customerDao.findAll(spec, sort).forEach(customer -> System.out.println(customer));
    }

    /**
     * 分页查询
     *      Specification: 查询条件
     *      Pageable：分页参数
     *          分页参数：查询的页码，每页查询的条数
     *          findAll(Specification,Pageable)：带有条件的分页
     *          findAll(Pageable)：没有条件的分页
     *  返回：Page（springDataJpa为我们封装好的pageBean对象，数据列表，共条数）
     */
    @Test
    public void testSpec4() {
        //PageRequest对象是Pageable接口的实现类
        /*
         * 创建PageRequest的过程中，需要调用他的构造方法传入两个参数
         *      第一个参数：当前查询的页数（从0开始）
         *      第二个参数：每页查询的数量
         */
        Pageable pageable = new PageRequest(0,2);
        //分页查询
        Page<Customer> page = customerDao.findAll(null, pageable);
        System.out.println(page.getContent()); //得到数据集合列表
        System.out.println(page.getTotalElements());//得到总条数
        System.out.println(page.getTotalPages());//得到总页数
    }
}
