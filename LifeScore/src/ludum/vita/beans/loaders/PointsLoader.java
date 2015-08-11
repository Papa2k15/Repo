/**
 * 
 */
package ludum.vita.beans.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ludum.vita.beans.PointsBean;

/**
 * @author Owner
 *
 */
public class PointsLoader implements BeanLoader<PointsBean> {

	@Override
	public PointsBean loadSingle(ResultSet rs) throws SQLException {
		return new PointsBean(rs.getString("LSUID"), rs.getLong("total"), rs.getLong("daily"),
				rs.getLong("weekly"), rs.getLong("monthly"), rs.getLong("yearly"));
	}

	@Override
	public List<PointsBean> loadList(ResultSet rs) throws SQLException {
		List<PointsBean> points = new LinkedList<PointsBean>();
		while (rs.next()) {
			points.add(loadSingle(rs));
		}
		return points;
	}
	

	@Override
	public PreparedStatement loadParameters(PreparedStatement ps,
			PointsBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
}
