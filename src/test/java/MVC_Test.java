import com.github.pagehelper.PageInfo;
import com.hunter.ssm_crud.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * 使用Spring测试模块提供的测试请求功能，测试curd请求的正确性
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:appContext.xml", "classpath:ssm_curd-servlet.xml"})
public class MVC_Test {
    //传入Spring MVC的IoC
    @Resource
    private WebApplicationContext context;
    //虚拟MVC请求，获取处理结果
    private MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        //模拟请求，拿到返回值
        MvcResult result = mockMvc.perform(
                                MockMvcRequestBuilders.get("/emps")
                                .param("pageNum", "5")).andReturn();

        //请求成功后，请求域中会有pageInfo，可以取出pageInfo进行验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码：" + pageInfo.getPageNum());
        System.out.println("总页码" + pageInfo.getPages());
        System.out.println("总记录数" + pageInfo.getTotal());
        System.out.println("需要连续显示的页码");
        int[] nums = pageInfo.getNavigatepageNums();
        for (int i : nums) {
            System.out.println(" " + i);
        }

        //获取员工数据
        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            System.out.println("ID: " + employee.getEmpId() + "==>Name: " + employee.getEmpName());
        }
    }
}
