package com.paulienvanalst.rugbymatch.events

import com.paulienvanalst.rugbymatch.team.TeamName
import org.springframework.context.ApplicationEvent
import org.springframework.context.event.EventListener

class StartGame(source: Any?, val hostingTeam: TeamName, val visitingTeam: TeamName) : ApplicationEvent(source!!)
class HalfTime(source: Any?) : ApplicationEvent(source!!)
class FinishGame(source: Any?) : ApplicationEvent(source!!)