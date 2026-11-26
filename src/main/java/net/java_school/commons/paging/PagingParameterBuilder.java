package net.java_school.commons.paging;

import java.util.Map;

public interface PagingParameterBuilder {
	void applyPaging(Map<String, String> map, int page, int recordsPerPage);
}