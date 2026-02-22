package com.github.sky_rebel.skybot.event;

/**
 * Represents the base class for all meta-events in the OneBot v11 protocol. This class extends
 * {@link OB11BaseEvent} and is designed to encapsulate common properties and methods specific to
 * meta-events. Meta-events are used to provide information about the bot's lifecycle, such as
 * when the bot goes online or offline, or when a heartbeat event is received. This class serves
 * as a foundation for more specific meta-event classes, each tailored to handle a particular kind
 * of meta-event.
 */
public class OB11BaseMetaEvent extends OB11BaseEvent {}