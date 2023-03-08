package com.smilebat.learntribe.dataaccess.config;

import lombok.ToString;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeFromIndexedValueContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

/**
 * Implementation for {@link ValueBridge} for Enum conversions
 *
 * <p>Copyright &copy; 2023 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@ToString
public class EnumToStringValueBridge implements ValueBridge<Enum, String> {

  private final Class<Enum> enumClass;

  /**
   * Constructor
   *
   * @param enumClass the Enum.
   */
  public EnumToStringValueBridge(Class<Enum> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public String toIndexedValue(Enum value, ValueBridgeToIndexedValueContext context) {
    if (value != null) {
      return value.name();
    }
    return null;
  }

  @Override
  public Enum fromIndexedValue(String value, ValueBridgeFromIndexedValueContext context) {
    if (value != null) {
      return Enum.valueOf(enumClass, value);
    }
    return null;
  }
}
