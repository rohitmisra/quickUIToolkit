package sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class SqlService {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private DataSource dataSource;
	
	public void setDataSource(final DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
	
	public List<Object[]> queryForList(final String sql) throws Exception {
        final List<Map<String,Object>> list = namedParameterJdbcTemplate.getJdbcOperations().queryForList(sql);
        final List<Object[]> resultlist = new ArrayList<Object[]>(list.size());
        for (final Map<String, Object> m : list) {
            resultlist.add(m.values().toArray());
		}
		
		return resultlist;
	}
	
	
	
}
