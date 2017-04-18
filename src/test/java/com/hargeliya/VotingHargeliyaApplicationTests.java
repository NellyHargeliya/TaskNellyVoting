package com.hargeliya;

import com.hargeliya.models.Theme;
import com.hargeliya.models.ThemeOption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VotingHargeliyaApplicationTests {

    private static final String themeTest_1 = "themeTest_1";
    private static final String optionTest_1 = "optionTest_11";
    private static final String optionTest_2 = "optionTest_12";
    private static final String themeTest_2 = "themeTest_2";
    private static final String optionTest_3 = "optionTest_21";
    private static final String optionTest_4 = "optionTest_22";
    private static boolean isSetUpDone = false;
    @Autowired
    private TestRestTemplate restTemplate;

    private Theme addTheme(String nameTheme, String... optionNames) {
        Theme result = new Theme();
        result.setNameTheme(nameTheme);
        List<ThemeOption> optionsList = new ArrayList<>();
        for (String name : optionNames) {
            ThemeOption option = new ThemeOption();
            option.setNameOption(name);
            option.setQuantity(0);
            option.setTheme(result);
            optionsList.add(option);
        }
        result.setThemeOptions(optionsList);
        return result;
    }

    @Before
    @Transactional
    public void setUp() {
        if (isSetUpDone) return;
        Theme theme = addTheme(themeTest_1, optionTest_1, optionTest_2);
        restTemplate.postForEntity("/themes", theme, Theme.class);

        theme = addTheme(themeTest_2, optionTest_3, optionTest_4);
        restTemplate.postForEntity("/themes", theme, Theme.class);

        theme = addTheme("themeTest3", "optionTest31");
        restTemplate.postForEntity("/themes", theme, Theme.class);

        restTemplate.exchange("/themes/1/open", HttpMethod.PUT, null, Theme.class);
        isSetUpDone = true;
    }

    @Test
    @Transactional
    public void getAllTheme() {
        ResponseEntity<Theme[]> responseEntity = restTemplate.getForEntity("/themes", Theme[].class);
        Theme[] allThemes = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(allThemes.length).isGreaterThan(0);
        assertThat(allThemes[0].getThemeOptions().size()).isGreaterThan(0);
    }

    @Test
    @Transactional
    public void getOneTheme() {
        ResponseEntity<Theme> responseEntity = restTemplate.getForEntity("/themes/1", Theme.class);
        Theme theme = responseEntity.getBody();
        List<ThemeOption> options = theme.getThemeOptions();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(theme.getNameTheme()).isEqualTo(themeTest_1);
        assertThat(options.size()).isEqualTo(2);
        assertThat(options.get(0).getNameOption()).isEqualTo(optionTest_1);
        assertThat(options.get(1).getNameOption()).isEqualTo(optionTest_2);
    }


    @Test
    @Transactional
    public void createTheme() {
        Theme theme = addTheme("createTestTheme",
                "createTestOption1", "createTestOption2", "createTestOption3", "createTestOption4");
        ResponseEntity<Theme> responseEntity = restTemplate.postForEntity("/themes", theme, Theme.class);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @Transactional
    public void createExistingTheme() {
        Theme theme = addTheme(themeTest_1);
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/themes", theme, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    @Transactional
    public void openAndEndTheme() {
        ResponseEntity<Theme> responseEntity = restTemplate.exchange("/themes/2/open",
                HttpMethod.PUT, null, Theme.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        responseEntity = restTemplate.exchange("/themes/2/end", HttpMethod.PUT, null, Theme.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        responseEntity = restTemplate.getForEntity("/themes/2", Theme.class);
        Theme result = responseEntity.getBody();
        assertThat(result.getStartDate()).isNotNull();
        assertThat(result.getEndDate()).isNotNull();
        assertThat(result.getUrlTheme()).isNotNull();
        responseEntity = restTemplate.exchange("/themes/2/open", HttpMethod.PUT, null, Theme.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
        responseEntity = restTemplate.exchange("/themes/2/end", HttpMethod.PUT, null, Theme.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }


    @Test
    @Transactional
    public void getOpenedThemes() {
        ResponseEntity<Theme[]> responseEntity = restTemplate.getForEntity("/opened", Theme[].class);
        Theme[] openedThemes = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(openedThemes.length).isEqualTo(1);
        assertThat(openedThemes[0].getNameTheme()).isEqualTo(themeTest_1);
        assertThat(openedThemes[0].getIdTheme()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void registerVote() {
        ResponseEntity<Theme> responseEntity = restTemplate.exchange("/themes/1/1",
                HttpMethod.PUT, null, Theme.class);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(responseEntity.getBody().getThemeOptions().get(0).getQuantity()).isEqualTo(1);
    }


    @Test
    @Transactional
    public void getInform() {
        ResponseEntity<Theme> responseEntity = restTemplate.getForEntity("/show/1", Theme.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


}
