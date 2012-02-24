package org.svenehrke.checkout.json.jackson;

import java.util.Arrays;

public class User {
	public enum Gender {MALE, FEMALE}

	public static class Name {
		private String _first, _last;

		public String getFirst() {
			return _first;
		}

		public String getLast() {
			return _last;
		}

		public void setFirst(String s) {
			_first = s;
		}

		public void setLast(String s) {
			_last = s;
		}

		@Override
		public boolean equals(Object json) {
			if (this == json) return true;
			if (json == null || getClass() != json.getClass()) return false;

			Name name = (Name) json;

			if (_first != null ? !_first.equals(name._first) : name._first != null) return false;
			if (_last != null ? !_last.equals(name._last) : name._last != null) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = _first != null ? _first.hashCode() : 0;
			result = 31 * result + (_last != null ? _last.hashCode() : 0);
			return result;
		}
	}

	private Gender _gender;
	private Name _name;
	private boolean _isVerified;
	private byte[] _userImage;

	public Name getName() {
		return _name;
	}

	public boolean isVerified() {
		return _isVerified;
	}

	public Gender getGender() {
		return _gender;
	}

	public byte[] getUserImage() {
		return _userImage;
	}

	public void setName(Name n) {
		_name = n;
	}

	public void setVerified(boolean b) {
		_isVerified = b;
	}

	public void setGender(Gender g) {
		_gender = g;
	}

	public void setUserImage(byte[] b) {
		_userImage = b;
	}

	@Override
	public boolean equals(Object json) {
		if (this == json) return true;
		if (json == null || getClass() != json.getClass()) return false;

		User user = (User) json;

		if (_isVerified != user._isVerified) return false;
		if (_gender != user._gender) return false;
		if (_name != null ? !_name.equals(user._name) : user._name != null) return false;
		if (!Arrays.equals(_userImage, user._userImage)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = _gender != null ? _gender.hashCode() : 0;
		result = 31 * result + (_name != null ? _name.hashCode() : 0);
		result = 31 * result + (_isVerified ? 1 : 0);
		result = 31 * result + (_userImage != null ? Arrays.hashCode(_userImage) : 0);
		return result;
	}
}