part of 'authentication_bloc.dart';

sealed class AuthenticationEvent {
  const AuthenticationEvent();
}

final class AuthInitialized extends AuthenticationEvent {}

class AuthenticationUserUpdated extends AuthenticationEvent {
  final AuthUser user;

  const AuthenticationUserUpdated(this.user);

  @override
  String toString() => 'AuthenticationUserUpdated { user: $user }';
}

final class AuthenticationLogoutRequested extends AuthenticationEvent {}
