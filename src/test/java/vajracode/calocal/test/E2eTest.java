package vajracode.calocal.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import vajracode.calocal.server.utils.ParamUtils;
import vajracode.calocal.shared.constants.DateConstants;
import vajracode.calocal.shared.model.*;

public class E2eTest extends CalocalTest {
	
	@Test
	public void main() {
		
		init();		
		
		authUser();
		
		mealGetEmpty();
		
		mealPostPut();
		
		mealGet();
		
		mealDelete();
		
		mealGetEmpty();
		
		logOut();
		
		authAdmin();
		
		userRUD();

		logOut();
	}

	private void userRUD() {
		UserDataList list = userTarget.request().get().readEntity(UserDataList.class);
		assertNotNull(list);
		
		UserData user = null;
		int size = list.getData().size();
		for (UserData u : list.getData()) {
			if (u.getId() == userId) {
				user = u;
				break;
			}
		}
		assertNotNull(user);
		
		assertNotNull(user = userTarget.path("/" + userId).request().get().readEntity(UserData.class));
		
		String newName = getRandomString();
		user.setName(newName);
		user = userTarget.path("/" + userId).request().put(getEntity(user)).readEntity(UserData.class);
		
		assertNotNull(user);
		assertEquals(newName, user.getName());
		
		assertEquals(Status.NO_CONTENT.getStatusCode(), 
				userTarget.path("/" + userId).request().delete().getStatus());
		
		list = userTarget.request().get().readEntity(UserDataList.class);
		assertNotNull(list);
		assertEquals(size - 1, list.getData().size());
	}

	private void mealDelete() {		
		MealDataList list = mealTarget.request().get().readEntity(MealDataList.class);
		assertNotNull(list);
		assertEquals(3, list.getData().size());
		
		for (MealData d : list.getData()) {
			assertEquals(Status.NO_CONTENT.getStatusCode(), 
					mealTarget.path("/" + d.getId()).request().delete().getStatus());
		}		
	}
	
	


	private void mealGet() {
		MealDataList list = mealTarget.request().get().readEntity(MealDataList.class);
		
		assertNotNull(list);
		assertEquals(3, list.getData().size());
				
		list = mealTarget.queryParam("fromDate", getDateBack(DateConstants.MILLIS_DAY * 2).getTime())
				.queryParam("toDate", new Date().getTime())
				.request().get().readEntity(MealDataList.class);
		
		assertNotNull(list);
		assertEquals(2, list.getData().size());
				
		int timeOffset = -TimeZone.getDefault().getOffset(new Date().getTime()) / 1000 / 60;
		
		list = mealTarget.queryParam("fromTime", LocalTime.of(9, 0).toString())
				.queryParam("toTime", LocalTime.of(13, 0).toString())
				.queryParam("timeOffset", timeOffset)
				.request().get().readEntity(MealDataList.class);
		
		assertNotNull(list);
		assertEquals(3, list.getData().size());
		
		list = mealTarget.queryParam("fromTime", LocalTime.of(9, 0).toString())
				.queryParam("toTime", LocalTime.of(10, 55).toString())
				.queryParam("timeOffset", timeOffset)
				.request().get().readEntity(MealDataList.class);
				
		assertNotNull(list);
		assertEquals(1, list.getData().size());
				
	}


	private void mealPostPut() {
		MealData wrong = getRandomMeal(null);
		wrong.setUserId(userId - 1);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), 
				mealTarget.request().post(getEntity(wrong)).getStatus());
		
		MealData in = getRandomMeal(null);		
		MealData ret1 = mealTarget
				.request()
				.post(getEntity(in))
				.readEntity(MealData.class);
		
		assertNotNull(ret1);
		assertEquals(in.getName(), ret1.getName());
		assertEquals(in.getCal(), ret1.getCal());
		assertEquals(userId, (long)ret1.getUserId());
						
		LocalDateTime ldt = LocalDateTime.now(), inLdt = ldt.withHour(10).withMinute(45);		
		ret1.setDateTime(ParamUtils.toDate(inLdt));
		ret1 = mealTarget.path("/" + ret1.getId()).request().put(getEntity(ret1)).readEntity(MealData.class);
		
		assertNotNull(ret1);
		LocalDateTime outLdt = ParamUtils.getDate(ret1.getDateTime().getTime());
		assertEquals(inLdt.getHour(), outLdt.getHour());
		assertEquals(inLdt.getMinute(), outLdt.getMinute());
		
		assertNotNull(mealTarget.request().post(getEntity(
				getRandomMeal(ParamUtils.toDate(ldt.minusDays(1).withHour(11)))))
				.readEntity(MealData.class));
						
		assertNotNull(mealTarget.request().post(
				getEntity(getRandomMeal(ParamUtils.toDate(ldt.minusDays(7).withHour(12)))))
				.readEntity(MealData.class));
	}	

	private MealData getRandomMeal(Date date) {
		return new MealData(getRandomString(), rand.nextInt(10000), date);
	}


	private void mealGetEmpty() {
		MealDataList list = mealTarget.request().get().readEntity(MealDataList.class);
		
		assertNotNull(list);
		assertEquals(0, list.getTotal());
		assertEquals(0, list.getData().size());
	}

}
