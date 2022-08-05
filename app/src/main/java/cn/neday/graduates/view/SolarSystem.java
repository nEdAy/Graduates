package cn.neday.graduates.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import cn.neday.graduates.R;

public class SolarSystem extends ViewGroup implements OnClickListener {
    // 九个位置定义
    private static final int LEFT_TOP = 0;
    private static final int LEFT_BOTTOM = 1;
    private static final int RIGHT_TOP = 2;
    private static final int RIGHT_BOTTOM = 3;
    private static final int CENTER_TOP = 4;
    private static final int CENTER_BOTTOM = 5;
    private static final int CENTER = 6;
    private static final int CENTER_LEFT = 7;
    private static final int CENTER_RIGHT = 8;

    private AnimationDrawable mAnimation;
    private Position position = Position.CENTER;// 默认位置
    private int radius;
    private MenuStatus menuStatus;
    private View centerMenu;// 默认选择最后一个控件为menu
    private onMenuItemClickListener menuItemClickListener;
    private onMenuItemLongClickListener menuItemLongClickListener;
    private Status status = Status.CLOSE;// 默认状态
    private boolean rotaFlag = true;// 中心按钮是否旋转

    public SolarSystem(Context context) {
        this(context, null);
    }

    public SolarSystem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SolarSystem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 半径默认值
        radius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                100, getResources().getDisplayMetrics());
        // 获取自定义属性的值
        TypedArray style = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SolarSystem, defStyleAttr, 0);

        int pos = style.getInt(R.styleable.SolarSystem_position, CENTER);
        switch (pos) {
            case LEFT_TOP:
                position = Position.LEFT_TOP;
                break;
            case LEFT_BOTTOM:
                position = Position.LEFT_BOTTOM;
                break;
            case RIGHT_TOP:
                position = Position.RIGHT_TOP;
                break;
            case RIGHT_BOTTOM:
                position = Position.RIGHT_BOTTOM;
                break;
            case CENTER_TOP:
                position = Position.CENTER_TOP;
                break;
            case CENTER_BOTTOM:
                position = Position.CENTER_BOTTOM;
                break;
            case CENTER:
                position = Position.CENTER;
                break;
            case CENTER_LEFT:
                position = Position.CENTER_LEFT;
                break;
            case CENTER_RIGHT:
                position = Position.CENTER_RIGHT;
                break;
            default:
                break;
        }
        // 获取半径值
        radius = (int) style.getDimension(R.styleable.SolarSystem_radius,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                        getResources().getDisplayMetrics()));
        style.recycle();// 释放
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRotaMenu(boolean flag) {
        rotaFlag = flag;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setOnMenuItemClickListener(
            onMenuItemClickListener menuItemClickListener) {
        this.menuItemClickListener = menuItemClickListener;
    }

    public void setOnMenuItemLongClickListener(
            onMenuItemLongClickListener menuItemLongClickListener) {
        this.menuItemLongClickListener = menuItemLongClickListener;
    }

    public void setOnMenuStatus(MenuStatus MenuStatus) {
        this.menuStatus = MenuStatus;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 获取宽高与模式
        int layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int layoutHeight = MeasureSpec.getSize(heightMeasureSpec);
        int HeightMode = MeasureSpec.getMode(heightMeasureSpec);
        // AT_MOST模式即wrap_content时测量父控件的宽高
        int width = 0;
        int height = 0;

        int childCount = getChildCount();// 子view的数量
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);// 测量子view
            // 获取子view的margin
            MarginLayoutParams lp = (MarginLayoutParams) childAt
                    .getLayoutParams();
            int childWidth = childAt.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            int childHeight = childAt.getMeasuredHeight() + lp.bottomMargin
                    + lp.topMargin;
            // 取出子view中最大的宽高
            width = Math.max(childWidth, width);
            height = Math.max(childHeight, height);
        }
        // 居于四个角时父控件的宽高
        width += radius;
        height += radius;
        // 居中时宽高翻倍
        if (position == Position.CENTER) {
            width += width;
            height += height;
        }
        // 居中且在顶部或底部时，宽翻倍
        if (position == Position.CENTER_BOTTOM
                || position == Position.CENTER_TOP) {
            width += width;
        }
        // 居中且在左或右边时高翻倍
        if (position == Position.CENTER_LEFT
                || position == Position.CENTER_RIGHT) {
            height += height;
        }
        // 判断宽高是否超越EXACTLY即match模式下的宽高
        if (layoutWidth - getPaddingLeft() - getPaddingRight() < width) {
            width = layoutWidth - getPaddingLeft() - getPaddingRight();
        }
        if (layoutHeight - getPaddingTop() - getPaddingBottom() < height) {
            height = layoutHeight - getPaddingTop() - getPaddingBottom();
        }
        // 载入宽高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? layoutWidth
                        : width + getPaddingLeft() + getPaddingRight(),
                HeightMode == MeasureSpec.EXACTLY ? layoutHeight : height
                        + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        // 获取宽高 padding 子view个数
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int layoutWidth = getWidth();
        int layoutHeight = getHeight();

        setMenuListener(childCount);
        for (int i = 0; i < childCount; i++) {

            View childAt = getChildAt(i);
            int childWidth = childAt.getMeasuredWidth();
            int childHeight = childAt.getMeasuredHeight();
            MarginLayoutParams lp = (MarginLayoutParams) childAt
                    .getLayoutParams();
            // 判断不同位置时所在的区域
            switch (position) {
                case LEFT_TOP:
                    paddingLeft = getPaddingLeft() + lp.leftMargin;
                    paddingTop = getPaddingTop() + lp.topMargin;
                    break;
                case LEFT_BOTTOM:
                    paddingLeft = getPaddingLeft() + lp.leftMargin;
                    paddingTop = layoutHeight - childHeight - lp.bottomMargin
                            - getPaddingBottom();
                    break;
                case RIGHT_TOP:

                    paddingLeft = layoutWidth - childWidth - lp.rightMargin
                            - getPaddingRight();
                    paddingTop = getPaddingTop() + lp.topMargin;

                    break;
                case RIGHT_BOTTOM:

                    paddingLeft = layoutWidth - childWidth - lp.rightMargin
                            - getPaddingRight();
                    paddingTop = layoutHeight - childHeight - lp.bottomMargin
                            - getPaddingBottom();
                    break;
                case CENTER_TOP:

                    paddingLeft = (layoutWidth - childWidth) / 2 + lp.leftMargin
                            + getPaddingLeft() - getPaddingRight();
                    paddingTop = getPaddingTop() + lp.topMargin;

                    break;
                case CENTER_BOTTOM:

                    paddingLeft = (layoutWidth - childWidth) / 2 + lp.leftMargin
                            + getPaddingLeft() - getPaddingRight();
                    paddingTop = layoutHeight - childHeight - lp.bottomMargin
                            - getPaddingBottom();

                    break;
                case CENTER:
                    paddingLeft = (layoutWidth - childWidth) / 2 + lp.leftMargin
                            + getPaddingLeft() - getPaddingRight();
                    paddingTop = (layoutHeight - childHeight) / 2 + lp.topMargin
                            + getPaddingTop() - getPaddingBottom();
                    break;
                case CENTER_LEFT:
                    paddingLeft = getPaddingLeft() + lp.leftMargin;
                    paddingTop = (layoutHeight - childHeight) / 2 + lp.topMargin
                            + getPaddingTop() - getPaddingBottom();
                    break;
                case CENTER_RIGHT:
                    paddingLeft = layoutWidth - childWidth - lp.rightMargin
                            - getPaddingRight();
                    paddingTop = (layoutHeight - childHeight) / 2 + lp.topMargin
                            + getPaddingTop() - getPaddingBottom();
                    break;

                default:
                    break;
            }
            childAt.layout(paddingLeft, paddingTop, paddingLeft + childWidth,
                    paddingTop + childHeight);
        }
    }

    /**
     * 中心按钮点击事件
     */
    private void setMenuListener(int childCount) {
        if (centerMenu == null) {
            centerMenu = getChildAt(childCount - 1);
            centerMenu.setOnClickListener(this);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    public void onClick(View v) {
        // 菜单状态切换
        if (menuStatus != null && status == Status.CLOSE)
            menuStatus.closeMenu();
        else if (menuStatus != null && status == Status.OPEN)
            menuStatus.openMenu();
        // 是否旋转
        if (rotaFlag)
            rotationMenu(v, 0f, 720f, 1000);
        // 子view弹出与收回
        toggleMenu(300);
    }

    /**
     * 子view的弹出与收回
     */
    public void toggleMenu(int Duration) {
        int childCount = getChildCount();
        ObjectAnimator animator01;
        ObjectAnimator animator02;
        // 弹出后子view的xy位置，去除最后一个子view
        float ix = 0;
        float iy = 0;
        for (int i = 0; i < childCount - 1; i++) {
            View childAt = getChildAt(i);
            switch (position) {
                case LEFT_TOP:
                    ix = (float) (radius * Math.cos(Math.PI / 2 / (childCount - 2)
                            * i));
                    iy = (float) (radius * Math.sin(Math.PI / 2 / (childCount - 2)
                            * i));
                    break;
                case LEFT_BOTTOM:
                    ix = (float) (radius * Math.cos(Math.PI / 2 / (childCount - 2)
                            * i));
                    iy = (float) (radius * Math.sin(Math.PI / 2 / (childCount - 2)
                            * i))
                            * -1;
                    break;
                case RIGHT_TOP:
                    ix = (float) (radius * Math.cos(Math.PI / 2 / (childCount - 2)
                            * i))
                            * -1;
                    iy = (float) (radius * Math.sin(Math.PI / 2 / (childCount - 2)
                            * i));
                    break;
                case RIGHT_BOTTOM:
                    ix = (float) (radius * Math.cos(Math.PI / 2 / (childCount - 2)
                            * i))
                            * -1;
                    iy = (float) (radius * Math.sin(Math.PI / 2 / (childCount - 2)
                            * i))
                            * -1;
                    break;
                case CENTER_TOP:
                    ix = (float) (radius * Math.cos(Math.PI / (childCount - 2) * i));
                    iy = (float) (radius * Math.sin(Math.PI / (childCount - 2) * i));
                    break;
                case CENTER_BOTTOM:
                    ix = (float) (radius * Math.cos(Math.PI / (childCount - 2) * i));
                    iy = (float) (radius * Math.sin(Math.PI / (childCount - 2) * i))
                            * -1;
                    break;
                case CENTER:
                    ix = (float) (radius * Math.cos(Math.PI * 2 / (childCount - 1)
                            * i));
                    iy = (float) (radius * Math.sin(Math.PI * 2 / (childCount - 1)
                            * i));
                    break;
                case CENTER_LEFT:
                    ix = (float) (radius * Math.sin(Math.PI / (childCount - 2) * i));

                    iy = (float) (radius * Math.cos(Math.PI / (childCount - 2) * i));

                    break;
                case CENTER_RIGHT:
                    ix = (float) (radius * Math.sin(Math.PI / (childCount - 2) * i))
                            * -1;

                    iy = (float) (radius * Math.cos(Math.PI / (childCount - 2) * i));
                    break;

                default:
                    break;
            }
            // 根据菜单的状态判断是弹出还是收回
            if (status == Status.CLOSE) {
                animator01 = ObjectAnimator.ofFloat(childAt, "translationX",
                        0F, ix);
                animator02 = ObjectAnimator.ofFloat(childAt, "translationY",
                        0F, iy);
            } else {
                animator01 = ObjectAnimator.ofFloat(childAt, "translationX",
                        ix, 0F);
                animator02 = ObjectAnimator.ofFloat(childAt, "translationY",
                        iy, 0F);
            }

            ObjectAnimator rotation = ObjectAnimator.ofFloat(childAt,
                    "rotation", 0f, 360f);
            AnimatorSet set = new AnimatorSet();
            set.setInterpolator(new BounceInterpolator());
            set.playTogether(animator01, animator02, rotation);
            set.setDuration(Duration);
            set.setStartDelay(i * 100L);// 每个的子view的延迟时间

            final int pos = i;
            childAt.setOnClickListener(v -> {
                if (menuItemClickListener != null)
                    menuItemClickListener.onItem(v, pos);
                // 子view点击之后的效果
                childAnimator(pos);
            });
            childAt.setOnLongClickListener(v -> {
                if (menuItemLongClickListener != null)
                    menuItemLongClickListener.onItem(v, pos);
                return false;
            });
            // 执行动画
            set.start();
        }
        // 执行完成后切换菜单状态
        changeStatus();
    }

    /**
     * 子view的动画效果
     */
    private void childAnimator(int pos) {
        for (int i = 0; i < getChildCount() - 1; i++) {
            View childAt = getChildAt(i);
            ObjectAnimator scaleX;
            if (i == pos) {// 让被选中的childat变大，其他变小
                scaleX = ObjectAnimator.ofFloat(childAt, "scaleX", 1f, 2f, 1f);
            } else {
                scaleX = ObjectAnimator.ofFloat(childAt, "scaleX", 1f, 0f, 1f);
            }
            // 透明化效果
            ObjectAnimator alpha = ObjectAnimator.ofFloat(childAt, "alpha", 1f,
                    0f, 1f);

            AnimatorSet set = new AnimatorSet();
            set.playTogether(alpha, scaleX);
            set.setDuration(300L * i);
            set.start();
        }
    }

    /**
     * 切换菜单状态
     */
    private void changeStatus() {
        status = (status == Status.OPEN ? Status.CLOSE : Status.OPEN);
    }

    /**
     * 主菜单旋转效果
     *
     * @param v 主菜单
     * @param f 起始值
     * @param g 结束时
     * @param i 时间
     */
    private void rotationMenu(View v, float f, float g, int i) {
        ObjectAnimator.ofFloat(v, "rotation", f, g).setDuration(i).start();
    }

    /**
     * 判断是打开还是关闭状态
     */
    public boolean isOpen() {
        return status == Status.OPEN;
    }

    // 定义位置的枚举类型
    public enum Position {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM, CENTER_TOP, CENTER_BOTTOM, CENTER, CENTER_LEFT, CENTER_RIGHT
    }

    // 定义菜单状态
    public enum Status {
        OPEN, CLOSE
    }

    // 子菜单点击事件监听
    public interface onMenuItemClickListener {
        void onItem(View view, int position);
    }

    // 子菜单长点击事件监听
    public interface onMenuItemLongClickListener {
        void onItem(View view, int position);
    }

    // 菜单状态监听
    public interface MenuStatus {
        void openMenu();

        void closeMenu();
    }

}
