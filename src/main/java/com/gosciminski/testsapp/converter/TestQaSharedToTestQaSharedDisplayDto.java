package com.gosciminski.testsapp.converter;

import com.gosciminski.testsapp.dto.display.TestQaSharedDisplayDto;
import com.gosciminski.testsapp.model.TestQaShared;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TestQaSharedToTestQaSharedDisplayDto implements Converter<TestQaShared, TestQaSharedDisplayDto> {

    private String appUrl;

	@Override
	public TestQaSharedDisplayDto convert(TestQaShared source) {
        TestQaSharedDisplayDto result = new TestQaSharedDisplayDto();
		result.setName(source.getTest().getName());
        result.setShareUrl(appUrl + "/testsolve/"+ source.getId() +"?secret=" + source.getSecret() + "&id=" + source.getId());
		return result;
    }

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
    
    


}