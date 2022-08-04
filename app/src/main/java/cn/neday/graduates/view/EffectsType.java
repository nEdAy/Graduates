package cn.neday.graduates.view;

import cn.neday.graduates.view.effects.BaseEffects;
import cn.neday.graduates.view.effects.Shake;

public enum EffectsType {
    DialogCancel(cn.neday.graduates.view.effects.DialogCancel.class), Shake(
            Shake.class);
    private final Class<? extends BaseEffects> effectsClazz;

    EffectsType(Class<? extends BaseEffects> mClass) {
        effectsClazz = mClass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects;
        try {
            bEffects = effectsClazz();
        } catch (ClassCastException | InstantiationException | IllegalAccessException e) {
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
