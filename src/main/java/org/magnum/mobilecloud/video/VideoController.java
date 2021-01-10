/*
 *
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.magnum.mobilecloud.video;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@Controller
public class VideoController {

    /**
     * You will need to create one or more Spring controllers to fulfill the
     * requirements of the assignment. If you use this file, please rename it
     * to something other than "AnEmptyController"
     * <p>
     * <p>
     * ________  ________  ________  ________          ___       ___  ___  ________  ___  __
     * |\   ____\|\   __  \|\   __  \|\   ___ \        |\  \     |\  \|\  \|\   ____\|\  \|\  \
     * \ \  \___|\ \  \|\  \ \  \|\  \ \  \_|\ \       \ \  \    \ \  \\\  \ \  \___|\ \  \/  /|_
     * \ \  \  __\ \  \\\  \ \  \\\  \ \  \ \\ \       \ \  \    \ \  \\\  \ \  \    \ \   ___  \
     * \ \  \|\  \ \  \\\  \ \  \\\  \ \  \_\\ \       \ \  \____\ \  \\\  \ \  \____\ \  \\ \  \
     * \ \_______\ \_______\ \_______\ \_______\       \ \_______\ \_______\ \_______\ \__\\ \__\
     * \|_______|\|_______|\|_______|\|_______|        \|_______|\|_______|\|_______|\|__| \|__|
     */
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    public @ResponseBody
    String goodLuck() {
        return "Good Luck!";
    }

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public @ResponseBody
    Collection<Video> getVideoList(Principal p) {
        return videoService.getAll();
    }

    @RequestMapping(value = "/video", method = RequestMethod.POST)
    public @ResponseBody
    Video addVideo(@RequestBody Video video) {
        return videoService.save(video);
    }

    @RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Video getVideoById(@PathVariable("id") long id) {
        return videoService.getVideoById(id);
    }

    @RequestMapping(value = "/video/{id}/like", method = RequestMethod.POST)
    public ResponseEntity like(@PathVariable("id") long id, Principal p) {
        return videoService.like(id, p.getName());
    }

    @RequestMapping(value = "/video/{id}/unlike", method = RequestMethod.POST)
    public ResponseEntity unlike(@PathVariable("id") long id, Principal p) {
        return videoService.unlike(id, p.getName());
    }

    @RequestMapping(value = "/video/search/findByName", method = RequestMethod.GET)
    public @ResponseBody
    Collection<Video> findByName(@RequestParam("title") String title) {
        return videoService.findByName(title);
    }

    @RequestMapping(value = "/video/search/findByDurationLessThan", method = RequestMethod.GET)
    public @ResponseBody
	Collection<Video> findByDurationLessThan(@RequestParam("duration") long duration) {
        return videoService.findByDurationLessThan(duration);
    }
}
