package p.martsulg.domain;


import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.CommentParams;
import p.martsulg.data.models.ListComments;

public class AddCommentUseCase extends UseCase<CommentParams,Object> {

    @Override
    protected Observable<Object> buildUseCase(CommentParams params) {
        return RestService.getInstance().addComment(params);
    }
}
