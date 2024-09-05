package Learn_REST_API;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.Test;

import REST_files.PayLoad;
import io.restassured.path.json.JsonPath;

public class Retrieve_JsonArray_Elements {

//	public static void main(String args[]) {
	@Test
		public void Retieve_Json () {
		int sum = 0;
		JsonPath js = new JsonPath(PayLoad.CoursePrice());

		// Get Course Size
		System.out.println("Get Course Size".toUpperCase());
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Get Purchase Amount
		System.out.println("Get Purchase Amount".toUpperCase());
		int Purchase_Amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(Purchase_Amount);

		// Get Courses title
		System.out.println("Get Courses title".toUpperCase());
		String title = js.get("courses.title[1]");
		System.out.println(title);

		// Print All course titles and their respective Prices
		System.out.println("Print All course titles and their respective Prices".toUpperCase());

		for (int i = 0; i < count; i++) {
			String All_Course_titles = js.get("courses.title[" + i + "]");
			System.out.println(All_Course_titles);

			System.out.println(js.get("courses.price[" + i + "]").toString());

			System.out.println(js.get("courses.copies[" + i + "]").toString());

		}
		// **** Print no of copies sold by RPA Course ****
		System.out.println("Print no of copies sold by RPA Course".toUpperCase());

		for (int i = 0; i < count; i++) {

			String Total = js.get("courses.title[" + i + "]");
			if (Total.equals("RPA")) {
				int final_count = js.get("courses.copies[" + i + "]");
				System.out.println(final_count);

				break;

			}
		}
		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");

//		int  count2 = js.get("courses.size();");

		for (int i = 0; i < count; i++) {
			int prices = js.get("courses.price[" + i + "]");
			int copies = js.get("courses.copies[" + i + "]");
			int amounts = prices * copies;
			System.out.println(amounts);
			sum = sum + amounts;
		}
		System.out.println(sum);
		int PurchaseAmount = js.get("dashboard.purchaseAmount");
		Assert.assertEquals(sum, PurchaseAmount);
	}

}
