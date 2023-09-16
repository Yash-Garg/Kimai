import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:kimai/data/models/models.dart';

part '../../generated/data/models/activity.freezed.dart';
part '../../generated/data/models/activity.g.dart';

@freezed
class Activity with _$Activity {
  const factory Activity({
    String? parentTitle,
    required int id,
    required String name,
    required String? comment,
    required bool visible,
    required bool billable,
    required List<MetaField> metaFields,
    required List<Team> teams,
    required String? color,
  }) = _Activity;

  factory Activity.fromJson(Map<String, dynamic> json) =>
      _$ActivityFromJson(json);
}

@freezed
class ActivityInner with _$ActivityInner {
  const factory ActivityInner({
    required int? project,
    required int id,
    required String name,
    required String comment,
    required bool visible,
    required bool billable,
    required String color,
    required String? number,
    required int? customer,
    required bool? globalActivities,
  }) = _ActivityInner;

  factory ActivityInner.fromJson(Map<String, dynamic> json) =>
      _$ActivityInnerFromJson(json);
}
