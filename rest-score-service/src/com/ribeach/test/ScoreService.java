package com.ribeach.test;

import javax.ws.rs.*;

@Path("/")
public class ScoreService {
	
	private static int wins, losses, ties;
	
	@GET
	@Path("/score")
	@Produces("application/json")
	public String getScore() {
	   String pattern = 
	      "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
	   return String.format(pattern, wins, losses, ties );   
	}
	
	// Design-wise PUT should be idempotent
	@PUT
	@Path("/score")
	@Produces("application/json")
	public String update(@QueryParam("wins") int wins, 
	                        @QueryParam("losses") int losses, 
	                        @QueryParam("ties")  int ties) {
	   ScoreService.wins   = wins;
	   ScoreService.losses = losses;
	   ScoreService.ties   = ties;
	   String pattern = 
	      "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
	   return String.format(pattern, wins, losses, ties);   
	}
	
	@POST @Path("/score/wins")@Produces("text/plain")
	public int increaseWins() { return ++wins; }
	
	@POST @Path("/score/losses")@Produces("text/plain")         
	public int increaseLosses() {return ++losses;}
	     
	@POST @Path("/score/ties")@Produces("text/plain")      
	public int increaseTies() { return ++ties;}

	@GET @Path("/score/wins")@Produces("text/plain")
	public int getWins() {return wins;}
	     
	@GET @Path("/score/losses")@Produces("text/plain")
	public int getLosses() {return losses;}
	     
	@GET @Path("/score/ties")@Produces("text/plain")
	public int getTies() {return ties;}
}
