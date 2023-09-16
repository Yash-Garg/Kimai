import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:kimai/data/api/kimai_repository.dart';
import 'package:kimai/data/models/auth_user.dart';

import 'authentication_state.dart';

part 'authentication_event.dart';

class AuthenticationBloc
    extends Bloc<AuthenticationEvent, AuthenticationState> {
  final KimaiRepository _repository;

  AuthenticationBloc({
    required KimaiRepository repository,
  })  : _repository = repository,
        super(const AuthenticationState.initial()) {
    on<AuthInitialized>(_onAuthInitialized);
    on<AuthenticationUserUpdated>(_onAuthenticationUserUpdated);
    on<AuthenticationLogoutRequested>(_onAuthenticationLogoutRequested);
  }

  FutureOr<void> _onAuthInitialized(
    AuthInitialized event,
    Emitter<AuthenticationState> emit,
  ) async {
    emit(const AuthenticationState.loading());
  }

  FutureOr<void> _onAuthenticationUserUpdated(
    AuthenticationUserUpdated event,
    Emitter<AuthenticationState> emit,
  ) {
    emit(AuthenticationState.authenticated(
      user: AuthUser(
        baseUrl: event.baseUrl,
        username: event.username,
        token: event.token,
      ),
    ));
  }

  FutureOr<void> _onAuthenticationLogoutRequested(
    AuthenticationLogoutRequested event,
    Emitter<AuthenticationState> emit,
  ) async {}
}
