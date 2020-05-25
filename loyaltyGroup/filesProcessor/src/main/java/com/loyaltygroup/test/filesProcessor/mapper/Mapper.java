package com.loyaltygroup.test.filesProcessor.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface Mapper<SOURCE, TARGET> {

    TARGET convert(SOURCE src, Supplier<TARGET> factory);
    TARGET convert(SOURCE src);

    SOURCE revert(TARGET targ, Supplier<SOURCE> factory);
    SOURCE revert(TARGET targ);

    default List<TARGET> convert(List<SOURCE> sources) {
        if (sources == null) {
            return Collections.emptyList();
        }
        return sources.stream().map(this::convert).collect(Collectors.toList());
    }
    default List<SOURCE> revert(List<TARGET> targets) {
        if (targets == null) {
            return Collections.emptyList();
        }
        return targets.stream().map(this::revert).collect(Collectors.toList());
    }
    default Set<SOURCE> revert(Set<TARGET> targets) {
        if (targets == null) {
            return Collections.emptySet();
        }
        return targets.stream().map(this::revert).collect(Collectors.toSet());
    }


}
