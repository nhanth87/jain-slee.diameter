package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.Enumerated;

/**
 * Enumerated class for LCS QoS Class.
 *
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public enum LCSQoSClass implements Enumerated {

  ASSURED(0),
  BEST_EFFORT(1);

  private int value;

  private LCSQoSClass(int value) {
    this.value = value;
  }

  @Override
  public int getValue() {
    return value;
  }

  public static LCSQoSClass fromInt(int type) {
    switch (type) {
      case 0:
        return ASSURED;
      case 1:
        return BEST_EFFORT;
      default:
        throw new IllegalArgumentException("Invalid LCS QoS Class value: " + type);
    }
  }
}
