package p.martsulg.domain;


import io.reactivex.Observable;
import p.martsulg.data.RestService;
import p.martsulg.data.models.AnswersParams;
import p.martsulg.data.models.CommentParams;
import p.martsulg.data.models.ListAnswers;
import p.martsulg.data.models.ListComments;

public class GetAnswersListUseCase extends UseCase<AnswersParams,ListAnswers> {

    @Override
    protected Observable<ListAnswers> buildUseCase(AnswersParams params) {
        return RestService.getInstance().getAnswers(params);
    }
}
