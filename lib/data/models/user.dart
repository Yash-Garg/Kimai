import 'package:freezed_annotation/freezed_annotation.dart';

part '../../generated/data/models/user.freezed.dart';
part '../../generated/data/models/user.g.dart';

@freezed
class UserEntity with _$UserEntity {
  const factory UserEntity({
    required String initials,
    required String alias,
    required String title,
    required String username,
    required String color,
  }) = _UserEntity;

  factory UserEntity.fromJson(Map<String, dynamic> json) =>
      _$UserEntityFromJson(json);
}
