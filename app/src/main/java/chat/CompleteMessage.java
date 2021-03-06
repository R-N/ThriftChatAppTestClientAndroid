/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package chat;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-10-22")
public class CompleteMessage implements org.apache.thrift.TBase<CompleteMessage, CompleteMessage._Fields>, java.io.Serializable, Cloneable, Comparable<CompleteMessage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CompleteMessage");

  private static final org.apache.thrift.protocol.TField CHATROOM_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("chatroomType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CHATROOM_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("chatroomId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("message", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CompleteMessageStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CompleteMessageTupleSchemeFactory());
  }

  /**
   * 
   * @see ChatroomType
   */
  public ChatroomType chatroomType; // required
  public long chatroomId; // required
  public Message message; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see ChatroomType
     */
    CHATROOM_TYPE((short)1, "chatroomType"),
    CHATROOM_ID((short)2, "chatroomId"),
    MESSAGE((short)3, "message");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CHATROOM_TYPE
          return CHATROOM_TYPE;
        case 2: // CHATROOM_ID
          return CHATROOM_ID;
        case 3: // MESSAGE
          return MESSAGE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __CHATROOMID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CHATROOM_TYPE, new org.apache.thrift.meta_data.FieldMetaData("chatroomType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, ChatroomType.class)));
    tmpMap.put(_Fields.CHATROOM_ID, new org.apache.thrift.meta_data.FieldMetaData("chatroomId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("message", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Message.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CompleteMessage.class, metaDataMap);
  }

  public CompleteMessage() {
  }

  public CompleteMessage(
    ChatroomType chatroomType,
    long chatroomId,
    Message message)
  {
    this();
    this.chatroomType = chatroomType;
    this.chatroomId = chatroomId;
    setChatroomIdIsSet(true);
    this.message = message;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CompleteMessage(CompleteMessage other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetChatroomType()) {
      this.chatroomType = other.chatroomType;
    }
    this.chatroomId = other.chatroomId;
    if (other.isSetMessage()) {
      this.message = new Message(other.message);
    }
  }

  public CompleteMessage deepCopy() {
    return new CompleteMessage(this);
  }

  @Override
  public void clear() {
    this.chatroomType = null;
    setChatroomIdIsSet(false);
    this.chatroomId = 0;
    this.message = null;
  }

  /**
   * 
   * @see ChatroomType
   */
  public ChatroomType getChatroomType() {
    return this.chatroomType;
  }

  /**
   * 
   * @see ChatroomType
   */
  public CompleteMessage setChatroomType(ChatroomType chatroomType) {
    this.chatroomType = chatroomType;
    return this;
  }

  public void unsetChatroomType() {
    this.chatroomType = null;
  }

  /** Returns true if field chatroomType is set (has been assigned a value) and false otherwise */
  public boolean isSetChatroomType() {
    return this.chatroomType != null;
  }

  public void setChatroomTypeIsSet(boolean value) {
    if (!value) {
      this.chatroomType = null;
    }
  }

  public long getChatroomId() {
    return this.chatroomId;
  }

  public CompleteMessage setChatroomId(long chatroomId) {
    this.chatroomId = chatroomId;
    setChatroomIdIsSet(true);
    return this;
  }

  public void unsetChatroomId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CHATROOMID_ISSET_ID);
  }

  /** Returns true if field chatroomId is set (has been assigned a value) and false otherwise */
  public boolean isSetChatroomId() {
    return EncodingUtils.testBit(__isset_bitfield, __CHATROOMID_ISSET_ID);
  }

  public void setChatroomIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CHATROOMID_ISSET_ID, value);
  }

  public Message getMessage() {
    return this.message;
  }

  public CompleteMessage setMessage(Message message) {
    this.message = message;
    return this;
  }

  public void unsetMessage() {
    this.message = null;
  }

  /** Returns true if field message is set (has been assigned a value) and false otherwise */
  public boolean isSetMessage() {
    return this.message != null;
  }

  public void setMessageIsSet(boolean value) {
    if (!value) {
      this.message = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CHATROOM_TYPE:
      if (value == null) {
        unsetChatroomType();
      } else {
        setChatroomType((ChatroomType)value);
      }
      break;

    case CHATROOM_ID:
      if (value == null) {
        unsetChatroomId();
      } else {
        setChatroomId((Long)value);
      }
      break;

    case MESSAGE:
      if (value == null) {
        unsetMessage();
      } else {
        setMessage((Message)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CHATROOM_TYPE:
      return getChatroomType();

    case CHATROOM_ID:
      return getChatroomId();

    case MESSAGE:
      return getMessage();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CHATROOM_TYPE:
      return isSetChatroomType();
    case CHATROOM_ID:
      return isSetChatroomId();
    case MESSAGE:
      return isSetMessage();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CompleteMessage)
      return this.equals((CompleteMessage)that);
    return false;
  }

  public boolean equals(CompleteMessage that) {
    if (that == null)
      return false;

    boolean this_present_chatroomType = true && this.isSetChatroomType();
    boolean that_present_chatroomType = true && that.isSetChatroomType();
    if (this_present_chatroomType || that_present_chatroomType) {
      if (!(this_present_chatroomType && that_present_chatroomType))
        return false;
      if (!this.chatroomType.equals(that.chatroomType))
        return false;
    }

    boolean this_present_chatroomId = true;
    boolean that_present_chatroomId = true;
    if (this_present_chatroomId || that_present_chatroomId) {
      if (!(this_present_chatroomId && that_present_chatroomId))
        return false;
      if (this.chatroomId != that.chatroomId)
        return false;
    }

    boolean this_present_message = true && this.isSetMessage();
    boolean that_present_message = true && that.isSetMessage();
    if (this_present_message || that_present_message) {
      if (!(this_present_message && that_present_message))
        return false;
      if (!this.message.equals(that.message))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_chatroomType = true && (isSetChatroomType());
    list.add(present_chatroomType);
    if (present_chatroomType)
      list.add(chatroomType.getValue());

    boolean present_chatroomId = true;
    list.add(present_chatroomId);
    if (present_chatroomId)
      list.add(chatroomId);

    boolean present_message = true && (isSetMessage());
    list.add(present_message);
    if (present_message)
      list.add(message);

    return list.hashCode();
  }

  @Override
  public int compareTo(CompleteMessage other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetChatroomType()).compareTo(other.isSetChatroomType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChatroomType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.chatroomType, other.chatroomType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetChatroomId()).compareTo(other.isSetChatroomId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChatroomId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.chatroomId, other.chatroomId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMessage()).compareTo(other.isSetMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.message, other.message);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("CompleteMessage(");
    boolean first = true;

    sb.append("chatroomType:");
    if (this.chatroomType == null) {
      sb.append("null");
    } else {
      sb.append(this.chatroomType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("chatroomId:");
    sb.append(this.chatroomId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("message:");
    if (this.message == null) {
      sb.append("null");
    } else {
      sb.append(this.message);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (message != null) {
      message.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CompleteMessageStandardSchemeFactory implements SchemeFactory {
    public CompleteMessageStandardScheme getScheme() {
      return new CompleteMessageStandardScheme();
    }
  }

  private static class CompleteMessageStandardScheme extends StandardScheme<CompleteMessage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CompleteMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CHATROOM_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.chatroomType = chat.ChatroomType.findByValue(iprot.readI32());
              struct.setChatroomTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CHATROOM_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.chatroomId = iprot.readI64();
              struct.setChatroomIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.message = new Message();
              struct.message.read(iprot);
              struct.setMessageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, CompleteMessage struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.chatroomType != null) {
        oprot.writeFieldBegin(CHATROOM_TYPE_FIELD_DESC);
        oprot.writeI32(struct.chatroomType.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(CHATROOM_ID_FIELD_DESC);
      oprot.writeI64(struct.chatroomId);
      oprot.writeFieldEnd();
      if (struct.message != null) {
        oprot.writeFieldBegin(MESSAGE_FIELD_DESC);
        struct.message.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CompleteMessageTupleSchemeFactory implements SchemeFactory {
    public CompleteMessageTupleScheme getScheme() {
      return new CompleteMessageTupleScheme();
    }
  }

  private static class CompleteMessageTupleScheme extends TupleScheme<CompleteMessage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CompleteMessage struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetChatroomType()) {
        optionals.set(0);
      }
      if (struct.isSetChatroomId()) {
        optionals.set(1);
      }
      if (struct.isSetMessage()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetChatroomType()) {
        oprot.writeI32(struct.chatroomType.getValue());
      }
      if (struct.isSetChatroomId()) {
        oprot.writeI64(struct.chatroomId);
      }
      if (struct.isSetMessage()) {
        struct.message.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CompleteMessage struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.chatroomType = chat.ChatroomType.findByValue(iprot.readI32());
        struct.setChatroomTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.chatroomId = iprot.readI64();
        struct.setChatroomIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.message = new Message();
        struct.message.read(iprot);
        struct.setMessageIsSet(true);
      }
    }
  }

}

