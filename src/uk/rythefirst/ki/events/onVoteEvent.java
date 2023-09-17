package uk.rythefirst.ki.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class onVoteEvent extends Event implements Cancellable {

	// Create a boolean to store whether the even is cancelled or not.
	private boolean isCancelled;

	// Created 2x Player instances to record who voted and who they voted for.
	private Player voter;
	private Player voted;

	// Create a HandlerList that we can return as our Handlers List.
	private static final HandlerList HANDLERS_LIST = new HandlerList();

	// Define the requirements to call the event.
	public onVoteEvent(Player voter, Player voted) {
		this.voted = voted;
		this.voter = voter;
	}

	// Return the player that cast the vote.
	public Player whoVoted() {
		return voter;
	}

	// Return the player that they voted for.
	public Player votedFor() {
		return voted;
	}

	// Return whether the event is cancelled or not.
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	// Set whether the event is cancelled or not.
	@Override
	public void setCancelled(boolean arg0) {
		isCancelled = arg0;
	}

	// Return the HandlerList.
	@Override
	public HandlerList getHandlers() {
		return HANDLERS_LIST;
	}

}
