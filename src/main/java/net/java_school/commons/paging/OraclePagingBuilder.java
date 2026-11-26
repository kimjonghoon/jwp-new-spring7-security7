package net.java_school.commons.paging;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("oracle")
public class OraclePagingBuilder implements PagingParameterBuilder {
	
    @Override
	public void applyPaging(Map<String, String> map, int page, int recordsPerPage) {
		int startRecord = (page - 1) * recordsPerPage + 1;
		int endRecord = page * recordsPerPage;
		
		map.put("start", String.valueOf(startRecord));
		map.put("end", String.valueOf(endRecord));
	}
}
