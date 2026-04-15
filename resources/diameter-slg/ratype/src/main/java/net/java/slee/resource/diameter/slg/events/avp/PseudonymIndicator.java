package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.Enumerated;

/**
 * Enumerated class for Pseudonym Indicator AVP (code 2530) as per 3GPP TS 29.172.
 *
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public enum PseudonymIndicator implements Enumerated {

  PSEUDONYM_REQUESTED(0),
  PSEUDONYM_NOT_REQUESTED(1);

  private int value;

  private PseudonymIndicator(int value) {
    this.value = value;
  }

  @Override
  public int getValue() {
    return value;
  }

  public static PseudonymIndicator fromInt(int type) {
    switch (type) {
      case 0:
        return PSEUDONYM_REQUESTED;
      case 1:
        return PSEUDONYM_NOT_REQUESTED;
      default:
        throw new IllegalArgumentException("Invalid Pseudonym Indicator value: " + type);
    }
  }
}
