package ludum.vita.beans.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ludum.vita.beans.ObjectiveBean;

public class ObjectiveLoader implements BeanLoader<ObjectiveBean> {

	@Override
	public List<ObjectiveBean> loadList(ResultSet rs) throws SQLException {
		List<ObjectiveBean> objectives = new LinkedList<ObjectiveBean>();
		while (rs.next()) {
			objectives.add(loadSingle(rs));
		}
		return objectives;
	}

	@Override
	public ObjectiveBean loadSingle(ResultSet rs) throws SQLException {
		ObjectiveBean objective = new ObjectiveBean(rs.getString("LSOID"),rs.getString("LSMID"),rs.getString("description"));
		objective.setTrackerValue(rs.getString("trackerVal"));
		return objective;
	}

	@Override
	public PreparedStatement loadParameters(PreparedStatement ps,
			ObjectiveBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
