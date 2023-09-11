import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:kimai/data/models/models.dart';

part '../../generated/data/models/team.freezed.dart';
part '../../generated/data/models/team.g.dart';

@freezed
class Team with _$Team {
  const factory Team({
    required int id,
    required String name,
    required List<Member>? members,
    required List<ActivityInner>? customers,
    required List<ActivityInner>? projects,
    required List<ActivityInner>? activities,
    required String color,
  }) = _Team;

  factory Team.fromJson(Map<String, dynamic> json) => _$TeamFromJson(json);
}
