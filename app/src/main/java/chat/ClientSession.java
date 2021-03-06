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
public class ClientSession implements org.apache.thrift.TBase<ClientSession, ClientSession._Fields>, java.io.Serializable, Cloneable, Comparable<ClientSession> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ClientSession");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField SESSION_FIELD_DESC = new org.apache.thrift.protocol.TField("session", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CERTIFICATE_FIELD_DESC = new org.apache.thrift.protocol.TField("certificate", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ClientSessionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ClientSessionTupleSchemeFactory());
  }

  public long id; // required
  public String session; // required
  public String certificate; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    SESSION((short)2, "session"),
    CERTIFICATE((short)3, "certificate");

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
        case 1: // ID
          return ID;
        case 2: // SESSION
          return SESSION;
        case 3: // CERTIFICATE
          return CERTIFICATE;
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
  private static final int __ID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SESSION, new org.apache.thrift.meta_data.FieldMetaData("session", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CERTIFICATE, new org.apache.thrift.meta_data.FieldMetaData("certificate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ClientSession.class, metaDataMap);
  }

  public ClientSession() {
  }

  public ClientSession(
    long id,
    String session,
    String certificate)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.session = session;
    this.certificate = certificate;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ClientSession(ClientSession other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetSession()) {
      this.session = other.session;
    }
    if (other.isSetCertificate()) {
      this.certificate = other.certificate;
    }
  }

  public ClientSession deepCopy() {
    return new ClientSession(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.session = null;
    this.certificate = null;
  }

  public long getId() {
    return this.id;
  }

  public ClientSession setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public String getSession() {
    return this.session;
  }

  public ClientSession setSession(String session) {
    this.session = session;
    return this;
  }

  public void unsetSession() {
    this.session = null;
  }

  /** Returns true if field session is set (has been assigned a value) and false otherwise */
  public boolean isSetSession() {
    return this.session != null;
  }

  public void setSessionIsSet(boolean value) {
    if (!value) {
      this.session = null;
    }
  }

  public String getCertificate() {
    return this.certificate;
  }

  public ClientSession setCertificate(String certificate) {
    this.certificate = certificate;
    return this;
  }

  public void unsetCertificate() {
    this.certificate = null;
  }

  /** Returns true if field certificate is set (has been assigned a value) and false otherwise */
  public boolean isSetCertificate() {
    return this.certificate != null;
  }

  public void setCertificateIsSet(boolean value) {
    if (!value) {
      this.certificate = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Long)value);
      }
      break;

    case SESSION:
      if (value == null) {
        unsetSession();
      } else {
        setSession((String)value);
      }
      break;

    case CERTIFICATE:
      if (value == null) {
        unsetCertificate();
      } else {
        setCertificate((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case SESSION:
      return getSession();

    case CERTIFICATE:
      return getCertificate();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case SESSION:
      return isSetSession();
    case CERTIFICATE:
      return isSetCertificate();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ClientSession)
      return this.equals((ClientSession)that);
    return false;
  }

  public boolean equals(ClientSession that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_session = true && this.isSetSession();
    boolean that_present_session = true && that.isSetSession();
    if (this_present_session || that_present_session) {
      if (!(this_present_session && that_present_session))
        return false;
      if (!this.session.equals(that.session))
        return false;
    }

    boolean this_present_certificate = true && this.isSetCertificate();
    boolean that_present_certificate = true && that.isSetCertificate();
    if (this_present_certificate || that_present_certificate) {
      if (!(this_present_certificate && that_present_certificate))
        return false;
      if (!this.certificate.equals(that.certificate))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true;
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_session = true && (isSetSession());
    list.add(present_session);
    if (present_session)
      list.add(session);

    boolean present_certificate = true && (isSetCertificate());
    list.add(present_certificate);
    if (present_certificate)
      list.add(certificate);

    return list.hashCode();
  }

  @Override
  public int compareTo(ClientSession other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSession()).compareTo(other.isSetSession());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSession()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.session, other.session);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCertificate()).compareTo(other.isSetCertificate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCertificate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.certificate, other.certificate);
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
    StringBuilder sb = new StringBuilder("ClientSession(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("session:");
    if (this.session == null) {
      sb.append("null");
    } else {
      sb.append(this.session);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("certificate:");
    if (this.certificate == null) {
      sb.append("null");
    } else {
      sb.append(this.certificate);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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

  private static class ClientSessionStandardSchemeFactory implements SchemeFactory {
    public ClientSessionStandardScheme getScheme() {
      return new ClientSessionStandardScheme();
    }
  }

  private static class ClientSessionStandardScheme extends StandardScheme<ClientSession> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ClientSession struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SESSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.session = iprot.readString();
              struct.setSessionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CERTIFICATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.certificate = iprot.readString();
              struct.setCertificateIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ClientSession struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      if (struct.session != null) {
        oprot.writeFieldBegin(SESSION_FIELD_DESC);
        oprot.writeString(struct.session);
        oprot.writeFieldEnd();
      }
      if (struct.certificate != null) {
        oprot.writeFieldBegin(CERTIFICATE_FIELD_DESC);
        oprot.writeString(struct.certificate);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ClientSessionTupleSchemeFactory implements SchemeFactory {
    public ClientSessionTupleScheme getScheme() {
      return new ClientSessionTupleScheme();
    }
  }

  private static class ClientSessionTupleScheme extends TupleScheme<ClientSession> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ClientSession struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetSession()) {
        optionals.set(1);
      }
      if (struct.isSetCertificate()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetId()) {
        oprot.writeI64(struct.id);
      }
      if (struct.isSetSession()) {
        oprot.writeString(struct.session);
      }
      if (struct.isSetCertificate()) {
        oprot.writeString(struct.certificate);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ClientSession struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.id = iprot.readI64();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.session = iprot.readString();
        struct.setSessionIsSet(true);
      }
      if (incoming.get(2)) {
        struct.certificate = iprot.readString();
        struct.setCertificateIsSet(true);
      }
    }
  }

}

