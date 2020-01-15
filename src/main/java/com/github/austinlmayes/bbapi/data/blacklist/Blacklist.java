package com.github.austinlmayes.bbapi.data.blacklist;

/**
 * A ban from the scoring for a specified IP address.
 *
 * @author Austin Mayes
 */
public class Blacklist {

  private final String ip;
  private final String reason;

  /**
   * @param ip that is being blacklisted
   * @param reason for the blacklist
   */
  Blacklist(String ip, String reason) {
    this.ip = ip;
    this.reason = reason;
  }

  /**
   * @return the IP that is blacklisted
   */
  String getIp() {
    return ip;
  }

  /**
   * @return the reason for the blacklist
   */
  String getReason() {
    return reason;
  }
}
