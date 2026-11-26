package net.java_school.commons.paging;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("mysql")
public class MysqlPagingBuilder implements PagingParameterBuilder {

	@Override
	public void applyPaging(Map<String, String> map, int page, int recordsPerPage) {
		int offset = (page - 1) * recordsPerPage;
		int rowCount = recordsPerPage;
		
		map.put("offset", String.valueOf(offset));
		map.put("rowCount", String.valueOf(rowCount));		
	}

}
