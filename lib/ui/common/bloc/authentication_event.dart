part of 'authentication_bloc.dart';

sealed class AuthenticationEvent {
  const AuthenticationEvent();
}

final class AuthInitialized extends AuthenticationEvent {}

class AuthenticationUserUpdated extends AuthenticationEvent {
  final String baseUrl;
  final String username;
  final String token;

  const AuthenticationUserUpdated({
    required this.baseUrl,
    required this.username,
    required this.token,
  });

  @override
  String toString() =>
      'AuthenticationUserUpdated { baseUrl: $baseUrl, username: $username, token: $token }';
}

final class AuthenticationLogoutRequested extends AuthenticationEvent {}
