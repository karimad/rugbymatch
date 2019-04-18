package com.paulienvanalst.rugbymatch.events

import com.paulienvanalst.rugbymatch.game.SetPiece
import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.ApplicationEvent

sealed class SetPieceEvent(source: Any, val setPiece: SetPiece, val winningTeam: TeamName) : ApplicationEvent(source)


open class ScrumWasPlayed(source: Any, scrum: SetPiece, winningTeam: TeamName) : SetPieceEvent(source, scrum, winningTeam) {

}

open class LineOutWasPlayed(source: Any, lineOut: SetPiece, winningTeam: TeamName) : SetPieceEvent(source, lineOut, winningTeam) {

}

fun List<SetPieceEvent>.scrumEvents() : List<SetPieceEvent> {
    return this.filter { setPieceEvent -> setPieceEvent is ScrumWasPlayed }
}

fun List<SetPieceEvent>.lineOutEvents() : List<SetPieceEvent> {
    return this.filter { setPieceEvent -> setPieceEvent is LineOutWasPlayed }
}

fun List<SetPieceEvent>.wonBy(teamName:TeamName) : List<SetPieceEvent> {
    return this.filter { setPieceEvent -> setPieceEvent.winningTeam == teamName}
}

fun List<SetPieceEvent>.lostBy(teamName:TeamName) : List<SetPieceEvent> {
    return this.filter { setPieceEvent -> setPieceEvent.winningTeam != teamName }
}
