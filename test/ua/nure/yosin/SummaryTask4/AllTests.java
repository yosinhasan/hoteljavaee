package ua.nure.yosin.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.yosin.SummaryTask4.db.bean.ActivitiesAlbumBeanTest;
import ua.nure.yosin.SummaryTask4.db.bean.BookingHistoryBeanTest;
import ua.nure.yosin.SummaryTask4.db.bean.FinanceBeanTest;
import ua.nure.yosin.SummaryTask4.db.bean.RequestsBeanTest;
import ua.nure.yosin.SummaryTask4.db.bean.ResponseBeanTest;
import ua.nure.yosin.SummaryTask4.db.bean.RoomAlbumBeanTest;
import ua.nure.yosin.SummaryTask4.db.bean.RoomBeanTest;
import ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbumTest;
import ua.nure.yosin.SummaryTask4.db.entity.ActivitiesTest;
import ua.nure.yosin.SummaryTask4.db.entity.FinanceTest;
import ua.nure.yosin.SummaryTask4.db.entity.RequestsTest;
import ua.nure.yosin.SummaryTask4.db.entity.ReservationTest;
import ua.nure.yosin.SummaryTask4.db.entity.ResponsesTest;
import ua.nure.yosin.SummaryTask4.db.entity.RoomAlbumTest;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypesTest;
import ua.nure.yosin.SummaryTask4.db.entity.RoomsTest;
import ua.nure.yosin.SummaryTask4.db.entity.UserTest;
import ua.nure.yosin.SummaryTask4.utils.DateUtilTest;
import ua.nure.yosin.SummaryTask4.web.validator.ValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ ActivitiesAlbumTest.class, ActivitiesTest.class, FinanceTest.class, RequestsTest.class,
		ReservationTest.class, ResponsesTest.class, RoomAlbumTest.class, RoomsTest.class, RoomTypesTest.class,
		UserTest.class, ActivitiesAlbumBeanTest.class, BookingHistoryBeanTest.class, FinanceBeanTest.class,
		RequestsBeanTest.class, ResponseBeanTest.class, RoomAlbumBeanTest.class, RoomBeanTest.class, DateUtilTest.class,
		ValidatorTest.class })
public class AllTests {

}
