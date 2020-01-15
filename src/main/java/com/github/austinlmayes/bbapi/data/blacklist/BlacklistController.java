package com.github.austinlmayes.bbapi.data.blacklist;

import com.github.austinlmayes.bbapi.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the blacklist API.
 *
 * @author Austin Mayes
 */
@RestController
public class BlacklistController {

  /**
   * @param ip to get a reason for
   * @return the reason an IP is on the blacklist, if it actually is
   */
  @GetMapping(value = "/blacklist/check")
  public String blacklistReason(@RequestParam("ip") String ip) {
    Blacklist found = Application.getInstance().getBlacklistManager().load(ip);
    if (found == null) {
      return "";
    }
    return found.getReason();
  }
}
