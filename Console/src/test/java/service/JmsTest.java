package service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhicheng.xu
 * @date 2015年5月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:/spring-mvc.xml","classpath:/applicationContext.xml","classpath:/spring-jms.xml"})
public class JmsTest {
//	@Autowired
//	private ProducerService producerService;
//	@Autowired
//	@Qualifier("queueDestination")
//	private Destination destination;
//
//	@Test
//	public void testSend() {
//		for (int i=0; i<20; i++) {
//			producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));
//		}
//	}
}
