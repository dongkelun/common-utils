package zx.soft.utils.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TimeUtilsTest {

	@Test
	public void testSINA_API_FORMAT() {
		Date date = TimeUtils.tranSinaApiDate("Sat May 08 05:06:45 +0800 2010");
		assertEquals("Sat May 08 05:06:45 CST 2010", date.toString());
	}

	@Test
	public void testTransToSolrDateStr() {
		String str = TimeUtils.transToSolrDateStr(System.currentTimeMillis());
		assertTrue(str.length() == 20);
		assertTrue(str.contains("T"));
		assertTrue(str.contains("Z"));
	}

	@Test
	public void testTransStrToCommonDateStr() {
		String str = TimeUtils.transToCommonDateStr(TimeUtils.transToSolrDateStr(System.currentTimeMillis()));
		assertTrue(str.length() == 19);
		assertFalse(str.contains("T"));
		assertFalse(str.contains("Z"));

		assertTrue(TimeUtils.transStrToCommonDateStr("Thu Apr 10 11:40:56 CST 2014").contains("10 11:40:56"));
	}

	@Test
	public void testTransLongToCommonDateStr() {
		String str = TimeUtils.transToCommonDateStr(System.currentTimeMillis());
		assertTrue(str.length() == 19);
		assertFalse(str.contains("T"));
		assertFalse(str.contains("Z"));
	}

	@Test
	public void testTransTimeStr() {
		assertEquals("2014-08-25T00:00:00Z", TimeUtils.transTimeStr("2014-08-25 00:00:00"));
	}

	@Test
	public void testTransTimeLong() {
		assertEquals(1408896000000L, TimeUtils.transTimeLong("2014-08-25 00:00:00"));
	}

	@Test
	public void testTransTwitterTimeLong() {
		assertEquals(1430495417083L, TimeUtils.transTwitterTimeLong("2015-05-01T23:50:17.083Z"));
	}

	@Test
	public void testGetMidnight() {
		long midight = TimeUtils.getMidnight(1430495417083L, -1);
		assertEquals("2015-04-30T00:00:00Z", TimeUtils.transToSolrDateStr(midight));
	}

	@Test
	public void testTransCurrentTime() {
		long time = TimeUtils.transCurrentTime(1430495417083L, 0, 0, -1, 0);
		assertEquals("2015-04-30T23:50:17Z", TimeUtils.transToSolrDateStr(time));
	}

}
