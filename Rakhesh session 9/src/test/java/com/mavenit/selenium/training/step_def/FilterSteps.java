package com.mavenit.selenium.training.step_def;

import com.mavenit.selenium.training.pages.ResultsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FilterSteps {


    private ResultsPage resultsPage = new ResultsPage();


    @And("^I apply filter \"([^\"]*)\" on search result$")
    public void iApplyFilterOnSearchResult(String filterValue) {
        resultsPage.selectFilterBy(filterValue);
    }


    @Then("^I should see all products \"([^\"]*)\" are filtered \"([^\"]*)\"$")
    public void iShouldSeeAllProductsAreFiltered(String filter, String filterValue) {
        if (filter.equalsIgnoreCase("review")) {
            List<Double> actual = resultsPage.getAllRatingOnProducts();
            assertThat("List is storing wrong value",
                    actual, everyItem(greaterThanOrEqualTo(Double.parseDouble(filterValue))));
        }
        if (filter.equalsIgnoreCase("range")) {
            List<Double> actual = resultsPage.getAllProductsPrices();
            List<String> rangeList = Arrays.asList(filterValue.split("-"));
            Double min = Double.parseDouble(rangeList.get(0));
            Double max = Double.parseDouble(rangeList.get(1));
            assertThat(actual, everyItem(is(both(greaterThanOrEqualTo(min)).and(lessThanOrEqualTo(max)))));
        }

    }
}
