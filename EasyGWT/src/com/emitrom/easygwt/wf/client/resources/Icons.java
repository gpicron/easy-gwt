/*******************************************************************************
 * This file is part of EasyGWT 
 * 
 * Copyright (c) 2011 Emitrom LLC
 * All rights reserved. 
 * 
 * EasyGWT is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU   General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 ******************************************************************************/
package com.emitrom.easygwt.wf.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Icons Bundle used by EasyGWT
 * 
 * @author Alfredo Quiroga-Villamil
 *
 */
public interface Icons extends ClientBundle {

    @Source("images/icons/start.gif")
    ImageResource start();
    
    @Source("images/icons/silk_asterisk.png")
    ImageResource silkAsterisk();
    
    @Source("images/icons/refresh.png")
    ImageResource refresh();
    
    @Source("images/icons/wrench.png")
    ImageResource wrench();
    
	@Source("images/icons/action_save.gif")
	ImageResource actionSave();

	@Source("images/icons/add.png")
	ImageResource add();

	@Source("images/icons/application_go.png")
	ImageResource applicationGo();

	@Source("images/icons/application_exit.png")
	ImageResource applicationExit();

	@Source("images/icons/audio-volume-animated.gif")
	ImageResource audioVolumeAnimated();

	@Source("images/icons/audio_volume_high.png")
	ImageResource audioVolumeHigh();

	@Source("images/icons/audio_volume_low.png")
	ImageResource audioVolumeLow();

	@Source("images/icons/audio_volume_muted.png")
	ImageResource audioVolumeMuted();
	
	@Source("images/icons/calendar.gif")
	ImageResource calendar();
	
	@Source("images/icons/silk_date_next.png")
    ImageResource silkDateNext();
	
	@Source("images/icons/cd_burn.png")
	ImageResource cdBurn();

	@Source("images/icons/cd_recordings.png")
	ImageResource cdRecordings();

	@Source("images/icons/comment.gif")
	ImageResource comment();
	
	@Source("images/icons/community.png")
	ImageResource community();
	
	@Source("images/icons/connected.png")
	ImageResource connected();

	@Source("images/icons/delete.png")
	ImageResource delete();
	
    @Source("images/icons/silk_delete.png")
    ImageResource silkDelete();
	
	@Source("images/icons/find.png")
	ImageResource find();

	@Source("images/icons/disconnect_icon.png")
	ImageResource disconnectIcon();

	@Source("images/icons/history_back.png")
	ImageResource historyBack();

	@Source("images/icons/history_forward.png")
	ImageResource historyForward();

	@Source("images/icons/house.png")
	ImageResource house();
	
	@Source("images/icons/key.gif")
	ImageResource key();
	
	@Source("images/icons/media_record.png")
	ImageResource mediaRecord();
	
	@Source("images/icons/list_components.gif")
	ImageResource listComponents();
	
	@Source("images/icons/list_users.gif")
	ImageResource listUsers();
	
	@Source("images/icons/lock.png")
	ImageResource lock();
	
	@Source("images/icons/messaging.png")
	ImageResource messaging();
	
	@Source("images/icons/new.png")
	ImageResource addNew();

	@Source("images/icons/page_refresh.gif")
	ImageResource pageRefresh();

	@Source("images/icons/phone.png")
	ImageResource phone();

	@Source("images/icons/power_off.png")
	ImageResource powerOff();

	@Source("images/icons/power_on.png")
	ImageResource powerOn();

	@Source("images/icons/silenced.png")
	ImageResource silenced();
	
	@Source("images/icons/table.gif")
	ImageResource table();
	
	@Source("images/icons/earmuffs-red.png")
	ImageResource earmuffs();
	
	@Source("images/icons/user.gif")
	ImageResource user();
	
	@Source("images/icons/silk_user_gray.png")
	ImageResource self();
	
	@Source("images/icons/settings.png")
	ImageResource settings();
	
	@Source("images/icons/chart_bar.png")
	ImageResource chartBart();

	@Source("images/icons/future_projects.png")
	ImageResource futureProject();

}