import 'package:freezed_annotation/freezed_annotation.dart';

part '../../generated/data/models/member.freezed.dart';
part '../../generated/data/models/member.g.dart';

@freezed
class Member with _$Member {
  const factory Member({
    required User user,
    @JsonKey(name: 'teamlead') @Default(false) bool teamLead,
  }) = _Member;

  factory Member.fromJson(Map<String, dynamic> json) => _$MemberFromJson(json);
}

@freezed
class User with _$User {
  const factory User({
    required String initials,
    required int id,
    required String alias,
    required String title,
    required String username,
    required String accountNumber,
    @Default(true) bool enabled,
    required String color,
  }) = _User;

  factory User.fromJson(Map<String, dynamic> json) => _$UserFromJson(json);
}
