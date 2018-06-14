import com.hunter.ssm_crud.bean.Employee;
import com.hunter.ssm_crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 测试DAO层的工作
 * 推荐Spring的项目使用Spring的单元测试，可以自动注入需要的组件
 * 1.导入SpringTest模块
 * 2.@ContextConfiguration指定Spring配置文件的位置
 * 3.直接autowired要使用的组件即可(autowired报错，改用@Resource)
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:appContext.xml"})
public class MapperTest {
//    @Resource
//    private DepartmentMapper departmentMapper;
//    @Resource
//    private EmployeeMapper employeeMapper;
    @Resource
    private SqlSession sqlSession;
    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD() {
        /*//1.插入几个部门
        departmentMapper.insertSelective(new Department(null, "开发部"));
        departmentMapper.insertSelective(new Department(null, "测试部"));*/

        //2.生成员工数据
        //employeeMapper.insertSelective(new Employee(null, "Jack", "M", "Jack@gmail.com", 1));

        //3.批量插入多个员工 使用可以执行批量操作的sqlsession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, uid, "M",
                                                uid + "@gmail.com", 1));
        }
    }
}
