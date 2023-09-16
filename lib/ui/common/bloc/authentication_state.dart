import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:kimai/data/models/auth_user.dart';

part '../../../generated/ui/common/bloc/authentication_state.freezed.dart';

@freezed
class AuthenticationState with _$AuthenticationState {
  const factory AuthenticationState.initial() = _Initial;

  const factory AuthenticationState.loading() = _Loading;

  const factory AuthenticationState.unauthenticated() = _UnAuthenticated;

  const factory AuthenticationState.authenticated({
    required AuthUser user,
  }) = _Authenticated;
}
