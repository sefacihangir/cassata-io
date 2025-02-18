/*
 * Copyright (c) 2018. cassata.io
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.cassata.service.processor;

import com.google.inject.Inject;
import io.cassata.commons.dal.EventsTableDao;
import io.cassata.commons.models.Event;
import io.cassata.commons.models.EventStatus;
import io.cassata.service.http.request.AddEventRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetRequestProcessor {
    @Inject
    private EventsTableDao eventsTableDao;

    public EventStatus getStatus(String appId, String eventId) {

        log.info("App Id: {}, Event Id: {}. Received get request.", appId, eventId);

        Event event = eventsTableDao.getEventById(appId, eventId);

        if (event == null) {
            log.info("App Id: {}, Event Id: {}. Event not found.", appId, eventId);
            return null;
        }

        log.info("App Id: {}, Event Id: {}. Returning status {}..", appId, eventId, event.getEventStatus());
        return event.getEventStatus();
    }
}
