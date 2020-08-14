package proyecto.pidetucomida.adaptadores;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItem extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemListener listener;
    public RecyclerItem(int dragDirs, int swipeDirs,RecyclerItemListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(viewHolder != null){
            View foregroundView = ((AdaptadorCarrito.ProductosViewHolder) viewHolder).LayoutBorrar;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }
    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {

        View foregroundView = ((AdaptadorCarrito.ProductosViewHolder) viewHolder).LayoutBorrar;

        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX,dY,
                actionState,isCurrentlyActive);

    }
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View foregroundView = ((AdaptadorCarrito.ProductosViewHolder) viewHolder).LayoutBorrar;
        getDefaultUIUtil().clearView(foregroundView);
    }
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {

        View foregroundView = ((AdaptadorCarrito.ProductosViewHolder) viewHolder).LayoutBorrar;

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);

    }
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwipe(viewHolder,viewHolder.getAdapterPosition());

    }
    public interface RecyclerItemListener{
        void onSwipe(RecyclerView.ViewHolder viewHolder,int position);

    }
}
