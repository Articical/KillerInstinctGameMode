package uk.rythefirst.ki.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class VoteFinishEvent extends Event implements Cancellable {

	private boolean isCancelled;

	private static final HandlerList HANDLERS_LIST = new HandlerList();

	private Integer voteType;
	private Integer responses;
	private Player voted;

	// Vote types.
	// 1 - Vote to Kill off.
	// 2 - Vote to revive.

	public VoteFinishEvent(Integer type, Player voted, Integer totalResponses) {

		voteType = type;
		this.voted = voted;
		responses = totalResponses;
	}

	public Integer getType() {
		return voteType;
	}

	public Player getResultingPlayer() {
		return voted;
	}

	public Integer getTotalResponses() {
		return responses;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.isCancelled = true;

	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS_LIST;
	}

}
