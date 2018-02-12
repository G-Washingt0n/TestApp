package p.martsulg.domain;


import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.CommentParams;
import p.martsulg.data.models.ListComments;
import p.martsulg.data.models.Profile;
import p.martsulg.data.models.UserModel;

public class GetCommentsListUseCase extends UseCase<CommentParams,ListComments> {

    @Override
    protected Observable<ListComments> buildUseCase(CommentParams params) {
        return RestService.getInstance().getComments(params);
    }
}
