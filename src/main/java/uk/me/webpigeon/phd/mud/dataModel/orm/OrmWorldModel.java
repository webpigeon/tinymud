package uk.me.webpigeon.phd.mud.dataModel.orm;

import java.sql.SQLException;
import java.util.Collection;

import com.j256.ormlite.dao.Dao;

import uk.me.webpigeon.phd.mud.modules.world.Direction;
import uk.me.webpigeon.phd.mud.modules.world.Room;
import uk.me.webpigeon.phd.mud.modules.world.RoomLink;
import uk.me.webpigeon.phd.mud.modules.world.WorldModel;

public class OrmWorldModel implements WorldModel {
	private Dao<Room,String> rooms;
	private Dao<RoomLink,String> links;
	
	public OrmWorldModel(Dao<Room,String> dao, Dao<RoomLink,String> links) {
		this.rooms = dao;
		this.links = links;
	}

	@Override
	public Room getPlayerRoom(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerRoom(String account, Room newRoom) {
		

	}

	@Override
	public void createRoom(Room room) {
		try {
			rooms.create(room);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void link(Room from, Room to, Direction direction) {
		try {
			RoomLink link = new RoomLink();
			link.from = from;
			link.to = to;
			link.direction = direction;
	
			links.create(link);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Room getRoomAt(String roomID) {
		try {
			return rooms.queryForId(roomID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Room getRoomAt(Room room, Direction direction) {
		RoomLink link = getLink(room, direction);
		if (link == null) {
			return null;
		}
		return link.to;
	}

	@Override
	public Collection<RoomLink> getExits(Room room) {
		return room.getLinks();
	}

	@Override
	public RoomLink getLink(Room room, Direction d) {
		for (RoomLink link : room.getLinks()) {
			if (d.equals(link.direction)) {
				return link;
			}
		}
		
		return null;
	}

}
