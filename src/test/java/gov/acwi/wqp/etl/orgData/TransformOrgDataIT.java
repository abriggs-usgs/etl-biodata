package gov.acwi.wqp.etl.orgData;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.acwi.wqp.etl.BiodataBaseFlowIT;
import gov.acwi.wqp.etl.orgData.index.BuildOrgDataIndexesFlowIT;
import gov.acwi.wqp.etl.orgData.table.SetupOrgDataSwapTableFlowIT;
import java.nio.charset.Charset;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

public class TransformOrgDataIT extends BiodataBaseFlowIT {
	
	@Autowired
	@Qualifier("orgDataFlow")
	private Flow orgDataFlow;
	
	public static final String EXPECTED_DATABASE_QUERY_INDEX = "";
	public static final String EXPECTED_DATABASE_QUERY_TABLE = "";
	
	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
	EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
	ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}
	
	@Before
	public void setup() {
		testJob = jobBuilderFactory.get("orgDataFlowTest")
				.start(orgDataFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}
	
	@Test
	@DatabaseSetup( connection="wqp", value="classpath:/testResult/wqp/orgData/empty.xml")
	@DatabaseSetup( connection="wqp", value="classpath:/testResult/wqp/station/station.xml")
	@ExpectedDatabase( connection="wqp", value="classpath:/testResult/wqp/orgData/orgData.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformOrgDataStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformOrgDataStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	@DatabaseSetup( connection="wqp", value="classpath:/testResult/wqp/orgData/empty.xml")
	@DatabaseSetup( connection="wqp", value="classpath:/testResult/wqp/station/station.xml")
	@ExpectedDatabase( value="classpath:/testResult/wqp/orgData/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
				table=BuildOrgDataIndexesFlowIT.EXPECTED_DATABASE_TABLE,
				query=EXPECTED_DATABASE_QUERY_INDEX)
	@ExpectedDatabase( connection="pg", value="classpath:/testResult/wqp/orgData/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
				table=SetupOrgDataSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
				query=EXPECTED_DATABASE_QUERY_TABLE)
	@ExpectedDatabase( connection="wqp", value="classpath:/testResult/wqp/orgData/orgData.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void orgDataFlowTest() {
		Job orgDataFlowTest = jobBuilderFactory.get("orgDataFlowTest")
					.start(orgDataFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(orgDataFlowTest);
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
