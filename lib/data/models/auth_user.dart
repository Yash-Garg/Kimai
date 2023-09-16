import 'dart:convert';

import 'package:equatable/equatable.dart';

class AuthUser extends Equatable {
  final String baseUrl;
  final String username;
  final String token;

  const AuthUser({
    required this.baseUrl,
    required this.username,
    required this.token,
  });

  @override
  List<Object?> get props => [baseUrl, username, token];

  @override
  String toString() {
    return 'AuthUser { baseUrl: $baseUrl, username: $username, token: $token }';
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'baseUrl': baseUrl,
      'username': username,
      'token': token,
    };
  }

  factory AuthUser.fromMap(Map<String, dynamic> map) {
    return AuthUser(
      baseUrl: map['baseUrl'] as String,
      username: map['username'] as String,
      token: map['token'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory AuthUser.fromJson(String source) =>
      AuthUser.fromMap(json.decode(source) as Map<String, dynamic>);
}
